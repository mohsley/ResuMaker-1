// var currentPage = 0;
// showPage(currentPage);
// //
// function showPage(n) {
//     var x = document.getElementsByClassName("page");
//     x[n].style.display = "block";
//     if (n == 0) {
//         document.getElementById("prevBtn").style.display = "none";
//     } else {
//         document.getElementById("prevBtn").style.display = "inline";
//     }
//
//     if (n == (x.length - 1)) {
//         document.getElementById("nextBtn").innerHTML = "Submit";
//     } else {
//         document.getElementById("nextBtn").innerHTML = "Next";
//     }
//
//     fixStepIndicator(n);
// }
//
// function nextPrev(n) {
//     var x = document.getElementsByClassName("page");
//     if (n == 1 && !validateForm()) return false;
//     x[currentPage].style.display = "none";
//     currentPage = currentPage + n;
//     if (currentPage >= x.length) {
//         document.getElementById("regForm").submit();
//         return false;
//     }
//     showPage(currentPage);
// }
//
// function validateForm() {
//     // This function deals with validation of the form fields
//     var x, y, i, valid = true;
//     x = document.getElementsByClassName("page");
//     y = x[currentPage].getElementsByTagName("input");
//     // A loop that checks every input field in the current tab:
//     for (i = 0; i < y.length; i++) {
//         // If a field is empty...
//         if (y[i].value == "") {
//             // add an "invalid" class to the field:
//             y[i].className += " invalid";
//             // and set the current valid status to false:
//             valid = false;
//         }
//     }
//     // If the valid status is true, mark the step as finished and valid:
//     if (valid) {
//         document.getElementsByClassName("step")[currentPage].className += " finish";
//     }
//     return valid; // return the valid status
// }
//
// function fixStepIndicator(n) {
//     // This function removes the "active" class of all steps...
//     var i, x = document.getElementsByClassName("step");
//     for (i = 0; i < x.length; i++) {
//         x[i].className = x[i].className.replace(" active", "");
//     }
//     //... and adds the "active" class to the current step:
//     x[n].className += " active";
// }