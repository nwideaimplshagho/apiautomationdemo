package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class SyncFlowStpDefinition {

	@Given("Organization {string} end point has been configured")
	public void organization_end_point_has_been_configured(String string) {
	}

	@When("Event group type {string} message type {string} embedded HL7 message with account {string} and global message id {string} has been sent to CDE via CIE")
	public void event_group_type_message_type_embedded_hl7_message_with_account_and_global_message_id_has_been_sent_to_cde_via_cie(
			String string, String string2, String string3, String string4) {
	}

	@Then("Return {string} response")
	public void return_response(String string) {
	}

	@And("Audit log should be created in Auditlog table with status as {string}")
	public void audit_log_should_be_created_in_auditlog_table_with_status_as(String string) {
	}

}