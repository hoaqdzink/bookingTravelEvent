var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
	// This function will display the specified tab of the form ...
	var x = document.getElementsByClassName("tab");
	x[n].style.display = "block";
	// ... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").style.display = "none";

	} else {
		document.getElementById("nextBtn").innerHTML = "Tiếp Tục";
		document.getElementById("nextBtn").style.display = "inline";
	}
	// ... and run a function that displays the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
	document.getElementById("uncheckthanhtoan").checked = false;
    uncheck()
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

function uncheck() {
    if (document.getElementById("uncheckthanhtoan").checked) {
        document.getElementById("thanhtoan").style.display = "inline"
    } else {
        document.getElementById("thanhtoan").style.display = "none"
    }
}


