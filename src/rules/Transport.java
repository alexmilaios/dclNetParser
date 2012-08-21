package rules;

public class Transport extends Rule {

	
	
	public Transport(Predicate _head, Literal_list _body) {
		super(_head, _body);
	}

	private String sendReceiveBind() {
		String output = "";
		output += "1 { receive_" + head.name.evaluate() + "(Receiver_,";
		output += head.terms.toString();
		output += "Sender_,SendTime_,ReceiveTime_)\n\t\t: extended_time(ReceiveTime_) } 1 :-";
		output += "\n\t\textended_time(SendTime_),\n\t\tsend_"+ head.name.evaluate();
		output += "(Sender_," + head.terms.toString() + "Receiver_,SendTime_).";
		return output;
	}
 	
	private String communication() {
		String output = "";
		output += "communication(Sender_,Receiver_,SendTime_,ReceiveTime_) :- ";
		output += "\n\t\textended_time(SendTime_),\n\t\textended_time(ReceiveTime_),";
		output += "\n\t\treceive_" + head.name.evaluate() + "(Receiver_," + head.terms.toString() + "Sender_,SendTime_,ReceiveTime_).";
		return output;	
	}
	
	private String messageSent() {
		String output = "";
		output += "message_sent(Sender_,SendTime_) :-";
		output += "\n\t\textended_time(SendTime_),";
		output += "\n\t\tsend_" + head.name.evaluate() + "(Sender_," + head.terms.toString() +"_,SendTime_).";
		return output;
	}
	
	private String messageReceived() {
		String output = "";
		output += "message_received(Receiver_,ReceiveTime_) :-";
		output += "\n\t\textended_time(ReceiveTime_),";
		output += "\n\t\treceive_" + head.name.evaluate() + "(Receiver_," + head.terms.toString() +"_,_,ReceiveTime_).";
		return output;
	}
	
	private String compressReceive() {
		String output = "";
		output += "receive_" + head.name.evaluate() + "(Self_," + head.terms.toString() + "X,Now_) :-";
		output += "\n\t\treceive_" + head.name.evaluate() + "(Self_," + head.terms.toString() + "X,_,Now_).";
		return output;
	}
	
	@Override
	public String evaluate() {
		String output;
		
		output = sendReceiveBind() + communication() + messageSent() + messageReceived() + compressReceive(); 
		return output;
	}

}
