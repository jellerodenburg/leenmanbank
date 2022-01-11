// Functions regarding Tabs
function showTabDetails(tabContentToShow) {
    hideAllTabs();

    // Show content of the selected tab
    document.getElementById(tabContentToShow).style.display = "block";
}

function hideAllTabs() {
    var i, tabContent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }
}

function showNextTab(nextTab, nextParagraph, currentParagraph) { //TODO
    document.getElementById(nextTab).style.display = "block";
    document.getElementById(nextParagraph).style.display = "block";
    hideDetails(currentParagraph);
}

//region Function regarding select options
function getRadioButtonChoice() {
    return document.querySelector('input[name="type"]:checked').value;
}

function getSelectChoice() {
    return document.getElementById("Sector").value;
}

function saveAccountType(id) {
    if (getRadioButtonChoice() === "Zakelijk") {
        document.getElementById(id).innerHTML = getRadioButtonChoice() + ", sector: " + getSelectChoice();
    } else {
        document.getElementById(id).innerHTML = getRadioButtonChoice();
    }
}

function checkAndOpenNextStep() {
    if (getRadioButtonChoice() === "Zakelijk") {
        // TODO set personalDetails to "none"
    } else {
        // TODO set companyDetails to "none"
    }

}
//endregion

//region Functions regarding Buttons
function showButton(id) {
    document.getElementById(id).style.display = "block";
}
//endregion

//region Helper functions
function showDetails(currentParagraph) {
    document.getElementById(currentParagraph).style.display = "block";
}

function hideDetails(previousParagraph) {
    document.getElementById(previousParagraph).style.display = "none";
}

function showElement(element) {
    document.getElementById(element).style.display = "block";
}

function hideElement(element) {
    document.getElementById(element).style.display = "none";
}
//endregion

