Feature: Synchroneous Message Routing

Scenario: Route embedded HL7 message to CIE for CDE via synchroneous flow
Given Organization "Orlando" end point has been configured
When Event group type "ADT" message type "A01" embedded HL7 message with account "1234" and global message id "aggf55y57" has been sent to CDE via CIE
Then Return "200" response
And Audit log should be created in Auditlog table with status as "Success"