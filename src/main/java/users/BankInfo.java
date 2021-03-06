package users;

import org.hibernate.validator.constraints.*;

public class BankInfo {

		String ba_id;

		@NotEmpty
		@Length(min = 2, max = 14, message="Account Name  Wrong. Select ACCOUNT NAME minimum 2 charaters to maximum 14")

		String account_name;
		
		@NotEmpty
		@Length(min = 5, max = 30, message="ROUNTING Number Wrong. Select ROUTING NUMBER minimum 5 charaters to maximum 14")


		String routing_number;

		@NotEmpty
		@Length(min = 10, max = 14, message="Card Info WRONG. Enter ACCOUNT NUMBER minimum 10 to maximum 14")

		String account_number;
		/**
		 * @param ba_id
		 * @param account_name
		 * @param routing_number
		 * @param account_number
		 */
		public BankInfo(String ba_id, String account_name,
				String routing_number, String account_number) {
			super();
			this.ba_id = ba_id;
			this.account_name = account_name;
			this.routing_number = routing_number;
			this.account_number = account_number;
		}
		public BankInfo(){
			
		}
		public String getBa_id() {
			return ba_id;
		}
		public void setBa_id(String ba_id) {
			this.ba_id = ba_id;
		}
		public String getAccount_name() {
			return account_name;
		}
		public void setAccount_name(String account_name) {
			this.account_name = account_name;
		}
		public String getRouting_number() {
			return routing_number;
		}
		public void setRouting_number(String routing_number) {
			this.routing_number = routing_number;
		}
		public String getAccount_number() {
			return account_number;
		}
		public void setAccount_number(String account_number) {
			this.account_number = account_number;
		}
		
}
