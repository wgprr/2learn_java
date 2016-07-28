package com.ringchi.bimp;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.ringchi.bimp.client.BimpClient;
import com.ringchi.bimp.message.BimpMessage;
import com.ringchi.bimp.message.impl.CmdPing;

public class ClientIoHandler extends IoHandlerAdapter {

	private Logger log = Logger.getLogger(ClientIoHandler.class.getName());

	private BimpClient client;

	public ClientIoHandler() {
		super();
	}

	public BimpClient getClient() {
		return client;
	}

	public void setClient(BimpClient client) {
		this.client = client;
	}

	public void sessionOpened(IoSession session) throws Exception {
		log.log(Level.INFO, "sessionOpened " + session);
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.log(Level.WARNING, cause.getMessage(), cause);
		// server.clientRemove(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.log(Level.INFO, "sessionClosed " + session);
		client.disconnect();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// log.log(Level.INFO, "sessionCreated " + session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// log.log(Level.INFO, "sessionIdle " + session);
		// super.sessionIdle(session, status);
		session.write(new CmdPing());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// super.messageSent(session, message);
		log.log(Level.INFO, "messageSent \n" + message.toString());
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		log.log(Level.INFO, "inputClosed " + session);
		client.disconnect();
		// super.inputClosed(session);
	}

	/**
	 * Handle incoming messages.
	 * 
	 * @see org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		BimpMessage msg = (BimpMessage) message;

		log.log(Level.INFO, "messageReceived \n" + msg.toReadableString());
		// BimpMessage resp = null;		

	}
}
