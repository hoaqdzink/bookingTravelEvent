const signinBtn = document.querySelector('.signinBtn');
const signupBtn = document.querySelector('.signupBtn');
const formBx = document.querySelector('.formBx');
const body = document.querySelector('body');


signupBtn.onclick = function() {
    formBx.classList.add('active')
    body.classList.add('active')
}

signinBtn.onclick = function() {
    formBx.classList.remove('active')
    body.classList.remove('active')
}

const showpass = document.querySelector(".showpass");

showpass.onclick = function() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
    var y = document.getElementById('hiden');
    if (y.className === 'fas fa-eye showpass') {
        y.className = 'fas fa-eye-slash showpass';
    } else {
        y.className = 'fas fa-eye showpass'
    }

}

function validate() {
    var u = document.getElementById("u").value;
    var p = document.getElementById("p").value;
    var p2 = document.getElementById("p2").value;
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var dc = document.getElementById("dc").value;

    var status = false;

    if (u == "" && p == "" && p2 == "" && name == "" && phone == "" && email == "" && dc == "") {
        alert("thieu kia!!!");
        status = false;
        clead();
    } else {
        window.location.href = "http://localhost:8080/login";
        clead();
        alert("Thanh cong");
        status = true;
    }

    function clead() {
        u.innerHTML = "";
        p.innerHTML = "";
        p2.innerHTML = "";
        name.innerHTML = "";
        phone.innerHTML = "";
        email.innerHTML = "";
        dc.innerHTML = "";
    }
    return status;
}