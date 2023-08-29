var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

const prevbtn = document.getElementById("prevBtn");
const nextbtn = document.getElementById("nextBtn");


function showTab(n) {
    // This function will display the specified tab of the form ...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    fixStepIndicator(n)
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");

    // Exit the function if any field in the current tab is invalid:
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= x.length) {
        //...the form gets submitted:
        document.getElementById("regForm").submit();
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class to the current step:
    x[n].className += " active";
}

var currentTab2 = 0; // Current tab is set to be the first tab (0)
showTab2(currentTab2); // Display the current tab

const prevbtn2 = document.getElementById("prevBtn2");
const nextbtn2 = document.getElementById("nextBtn2");


function showTab2(n) {
    // This function will display the specified tab of the form ...
    var x = document.getElementsByClassName("tab2");
    x[n].style.display = "block";
    // ... and run a function that displays the correct step indicator:
    fixStepIndicator2(n)
}

function nextPrev2(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab2");

    // Hide the current tab:
    x[currentTab2].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab2 = currentTab2 + n;
    // if you have reached the end of the form... :
    if (currentTab2 >= x.length) {
        //...the form gets submitted:
        document.getElementById("regForm2").submit();
        return false;
    }
    // Otherwise, display the correct tab:
    showTab2(currentTab2);
}

function fixStepIndicator2(n) {
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step2");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class to the current step:
    x[n].className += " active";
}