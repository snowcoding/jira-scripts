import com.onresolve.jira.groovy.user.FieldBehaviours
import org.apache.log4j.Logger
import org.apache.log4j.Level
import groovy.transform.BaseScript
@BaseScript FieldBehaviours fieldBehaviours

// Get the logger and set the log level
def log = Logger.getLogger(getClass())
log.setLevel(Level.DEBUG)

// Anytime this field changes, grab the field by it's ID, this function getFieldByID must be 
// available when writing a Field script:
def checkBoxField = getFieldById(fieldChanged)
def checkBoxFieldValue = checkBoxField.value

// Get the fields we want to control in terms of visibility:
def apac = getFieldByName('QPS APAC')
def emea = getFieldByName('QPS EMEA')
def west = getFieldByName('QPS US-West')
def east = getFieldByName('QPS US-East')

// Every time this field changes, set control fields to Hidden:
apac.setHidden(true)
emea.setHidden(true)
east.setHidden(true)
west.setHidden(true)

// When the checkbox only has 1 checkbox selected, it will be String:
if (checkBoxFieldValue in String) {
  
  if (checkBoxFieldValue == "APAC") {
    apac.setHidden(false)
    apac.setRequired(true)
  }
  if (checkBoxFieldValue == "EMEA") {
    emea.setHidden(false)
    emea.setRequired(true)
  }
  if (checkBoxFieldValue == "US-East") {
    east.setHidden(false)
    east.setRequired(true)
  }
  if (checkBoxFieldValue == "US-West") {
    west.setHidden(false)
    west.setRequired(true)
  }
// If there are more than one checkbox selected, it will be an ArrayList:
} else if (checkBoxFieldValue in ArrayList) {
  // Collect the selected checkbox values
  def chosenValuesList = []
  
  chosenValuesList.addAll(checkBoxFieldValue)

  for (chosenValue in chosenValuesList) {
    if (chosenValue == "APAC") {
      apac.setHidden(false)
      apac.setRequired(true)
    }
    if (chosenValue == "EMEA") {
      emea.setHidden(false)
      emea.setRequired(true)
    }
    if (chosenValue == "US-East") {
      east.setHidden(false)
      east.setRequired(true)
    }
    if (chosenValue == "US-West") {
      west.setHidden(false)
      west.setRequired(true)
    }
  }
} 
