// Get the modal
// Get DOM Elements
const modal = document.getElementById('myModal');
const closeBtn = document.querySelector('.close');

// Events

closeBtn.addEventListener('click', closeModal);
window.addEventListener('click', outsideClick);



// Close
function closeModal() {
    modal.style.display = 'none';
}

// Close If Outside Click
function outsideClick(e) {
    if (e.target == modal) {
        modal.style.display = 'none';
    }
}







// var modal = document.getElementById("myModal");
var modalFeedBack = document.getElementById("modalFeedBack");
var modalFeedBackEvent = document.getElementById("modalFeedBackEvent");

// Get the <span> element that closes the modal
// var span = document.querySelector(".close");
// When the user clicks on <span> (x), close the modal
var closefreeback1 = document.querySelectorAll(".close")[0];
var closefreeback2 = document.querySelectorAll(".close")[1];


// span.onclick = function() {
//     modal.style.display = "none";
// }

window.onclick = function(event) {

    // if (event.target == modal) {
    //     modal.style.display = "none";
    // }
    if (event.target == modalFeedBack) {
        modalFeedBack.style.display = "none";
    }
    if (event.target == modalFeedBackEvent) {
        modalFeedBackEvent.style.display = "none";
    }
}
closefreeback1.onclick = function() {
    modalFeedBack.style.display = "none";
}
closefreeback2.onclick = function() {
        modalFeedBackEvent.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it



function hienModalTour(name, id) {
    $('#nameTour').text(name);
    $('#idTour').text(id);
    $('#xacNhan1').attr('href', '/enterprise/tour_delete/' + id);
    modal.style.display = "block";
}

function hienModalEvent(name, id) {
    $('#nameEvent').text(name);
    $('#idEvent').text(id);
    $('#xacNhan2').attr('href', '/enterprise/event_delete/' + id);
    modal.style.display = "block";
}

function hienModalAccount(name, username) {
    $('#nameUsername').text(name);
    $('#xacNhan3').attr('href', '/admin/account_delete/' + username);
    modal.style.display = "block";
}

function hienModalFeedbackTour(folder, image, nameUser, comment, idrate, stars, orderTourId, email, emailEnterprise) {
    $('#hinhAnhAccount').attr('src', '/assets/images/account/' + folder + '/' + image);
    $('#tenUser').text(nameUser);
    $('#commentUser').html(comment);
    $('#idRatingTour').val(idrate);
    $('#comment').val(comment);
    $('#numstars').val(stars);
    $('#orderTourId').val(orderTourId);
    $('#emailRate').val(email);
    $('#emailEnterprise').val(emailEnterprise);
    $('#xacNhan4').submit;
    modalFeedBack.style.display = "block";
}

function hienModalFeedbackEvent(folder, image, nameUserEvent, comment, idrate, stars, orderEventId, email, emailEnterprise) {
    $('#hinhAnhAccountEvent').attr('src', '/assets/images/account/' + folder + '/' + image);
    $('#tenUserEvent').text(nameUserEvent);
    $('#commentUserEvent').html(comment);
    $('#idRatingEvent').val(idrate);
    $('#commentEvent').val(comment);
    $('#numstarsEvent').val(stars);
    $('#orderEventId').val(orderEventId);
    $('#emailRateEvent').val(email);
    $('#emailEnterpriseEvent').val(emailEnterprise);

    $('#xacNhan5').submit;
    modalFeedBackEvent.style.display = "block";

}

function showModalKhoiPhucTour(restoreTourName, restoreTourId) {
    $('#restoreTourName').text(restoreTourName);
    $('#restoreTourId').text(restoreTourId);
    $('#restoreTourXacNhan').attr('href', '/enterprise/get_khoi_phuc_tour/' + restoreTourId);
    modal.style.display = "block";
}

function showModalKhoiPhucEvent(restoreEventName, restoreEventId) {
    $('#restoreEventName').text(restoreEventName);
    $('#restoreEventId').text(restoreEventId);
    $('#restoreEventXacNhan').attr('href', '/enterprise/get_khoi_phuc_event/' + restoreEventId);
    modal.style.display = "block";
}