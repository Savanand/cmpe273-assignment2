package users;

public class BankDetails {

	
		String message;
		String change_date;
		String office_code;
		String record_type_code;
		String new_routing_number;
		String rn;
		String state;
		String address;
		String telephone;
		String data_view_code;
		String code;
		String customer_name;
		String city;
		String routing_number;
		String institution_status_code;
		String zip;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getChange_date() {
			return change_date;
		}
		public void setChange_date(String change_date) {
			this.change_date = change_date;
		}
		public String getOffice_code() {
			return office_code;
		}
		public void setOffice_code(String office_code) {
			this.office_code = office_code;
		}
		public String getRecord_type_code() {
			return record_type_code;
		}
		public void setRecord_type_code(String record_type_code) {
			this.record_type_code = record_type_code;
		}
		public String getNew_routing_number() {
			return new_routing_number;
		}
		public void setNew_routing_number(String new_routing_number) {
			this.new_routing_number = new_routing_number;
		}
		public String getRn() {
			return rn;
		}
		public void setRn(String rn) {
			this.rn = rn;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getData_view_code() {
			return data_view_code;
		}
		public void setData_view_code(String data_view_code) {
			this.data_view_code = data_view_code;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCustomer_name() {
			return customer_name;
		}
		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getRouting_number() {
			return routing_number;
		}
		public void setRouting_number(String routing_number) {
			this.routing_number = routing_number;
		}
		public String getInstitution_status_code() {
			return institution_status_code;
		}
		public void setInstitution_status_code(String institution_status_code) {
			this.institution_status_code = institution_status_code;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public BankDetails(String message, String change_date,
				String office_code, String record_type_code,
				String new_routing_number, String rn, String state,
				String address, String telephone, String data_view_code,
				String code, String customer_name, String city,
				String routing_number, String institution_status_code,
				String zip) {
			super();
			this.message = message;
			this.change_date = change_date;
			this.office_code = office_code;
			this.record_type_code = record_type_code;
			this.new_routing_number = new_routing_number;
			this.rn = rn;
			this.state = state;
			this.address = address;
			this.telephone = telephone;
			this.data_view_code = data_view_code;
			this.code = code;
			this.customer_name = customer_name;
			this.city = city;
			this.routing_number = routing_number;
			this.institution_status_code = institution_status_code;
			this.zip = zip;
		}
		public BankDetails() {
			super();
		}
		
}
