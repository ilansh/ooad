package UnitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import GameLibrary.Message;
import GameLibrary.ShowMessageType;

/**
 * Class containing unit tests for the Message object.
 * @author Ehud Doron (udid), Ortal Azoulay (ortalit)
 *
 */
public class MessageTest {

	@Test
	public void CreateMessageCheckStringTest() {
		Message msg = new Message("After move message", ShowMessageType.AFTER_MOVE);
		assertEquals("The message string should be after move message", "After move message", msg.getText());
	}
	
	@Test
	public void CreateMessageCheckTypeTest() {
		Message msg = new Message("After move message", ShowMessageType.AFTER_MOVE);
		assertEquals("The message type should be after move type", ShowMessageType.AFTER_MOVE, msg.getType());
	}
	
	@Test
	public void CreateMessageAndSetStringTest() {
		Message msg = new Message("message", ShowMessageType.AFTER_MOVE);
		msg.setText("new message");
		assertEquals("The message string should be new message", "new message", msg.getText());
	}
	
	@Test
	public void CreateMessageAndSetTypeTest() {
		Message msg = new Message("After move message", ShowMessageType.AFTER_MOVE);
		msg.setType(ShowMessageType.WINNER);
		assertEquals("The message type should be winner type", ShowMessageType.WINNER, msg.getType());
	}

}
