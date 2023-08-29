
const slidePage = document.querySelector(".slide-page");
const nextBtnFirst = document.querySelector(".firstNext");
const prevBtnSec = document.querySelector(".prev-1");
const nextBtnSec = document.querySelector(".next-1");
const prevBtnFourth = document.querySelector(".prev-3");
const submitBtn = document.querySelector(".submit");
const bullet = document.querySelectorAll(".bullet");
const step = document.querySelectorAll(".step");
let current = 1;


function isChecked(){
    if(document.getElementById("checkbox").checked){
        submitBtn.style.display = "block";
        submitBtnnd.style.display = "block";
    }
    else{
        submitBtn.style.display = "none";
        submitBtnnd.style.display = "none";
    }
}
function isCheckednd(){
    if(document.getElementById("checkbox-nd").checked){
        submitBtnnd.style.display = "block";
    }
    else{
        submitBtnnd.style.display = "none";
    }
}

nextBtnFirst.addEventListener("click", function(event){
  event.preventDefault();
  slidePage.style.marginLeft = "-25%";
  bullet[current - 1].classList.add("active");

  step[current - 1].classList.add("active");

  current += 1;
});
nextBtnSec.addEventListener("click", function(event){
  event.preventDefault();
  slidePage.style.marginLeft = "-50%";
  bullet[current - 1].classList.add("active");

  step[current - 1].classList.add("active");
  current += 1;
});
submitBtn.addEventListener("click", function(){
  bullet[current - 1].classList.add("active");

  step[current - 1].classList.add("active");
  current += 1;
  setTimeout(function(){
    alert("Your Form Successfully Signed up");
    location.reload();
  },800);
});
prevBtnSec.addEventListener("click", function(event){
  event.preventDefault();
  slidePage.style.marginLeft = "0%";
  bullet[current - 2].classList.remove("active");
  
  step[current - 2].classList.add("active");
  current -= 1;
});
prevBtnFourth.addEventListener("click", function(event){
  event.preventDefault();
  slidePage.style.marginLeft = "-25%";
  bullet[current - 2].classList.remove("active");
  
  step[current - 2].classList.add("active");
  current -= 1;
});


//dk nguoi dung
const slidePagend = document.querySelector(".slide-page-nd");
const nextBtnFirstnd = document.querySelector(".firstNext-nd");
const prevBtnSecnd = document.querySelector(".prev-1-nd");
const nextBtnSecnd = document.querySelector(".next-1-nd");
const prevBtnFourthnd = document.querySelector(".prev-3-nd");
const submitBtnnd = document.querySelector(".submit-nd");
const bulletnd = document.querySelectorAll(".bullet-nd");
const stepnd = document.querySelectorAll(".step-nd");
let currentnd = 1;


nextBtnFirstnd.addEventListener("click", function(event){
    event.preventDefault();
    slidePagend.style.marginLeft = "-25%";
    bulletnd[currentnd - 1].classList.add("active");
  
    stepnd[currentnd - 1].classList.add("active");
  
    currentnd += 1;
  });
  nextBtnSecnd.addEventListener("click", function(event){
    event.preventDefault();
    slidePagend.style.marginLeft = "-50%";
    bulletnd[currentnd - 1].classList.add("active");
  
    stepnd[currentnd - 1].classList.add("active");
    currentnd += 1;
  });
  submitBtnnd.addEventListener("click", function(){
    bulletnd[currentnd - 1].classList.add("active");
  
    stepnd[currentnd - 1].classList.add("active");
    currentnd += 1;
    setTimeout(function(){
      alert("Your Form Successfully Signed up");
      location.reload();
    },800);
  });
  prevBtnSecnd.addEventListener("click", function(event){
    event.preventDefault();
    slidePagend.style.marginLeft = "0%";
    bulletnd[currentnd - 2].classList.remove("active");
    
    stepnd[currentnd - 2].classList.add("active");
    currentndnd -= 1;
  });
  prevBtnFourthnd.addEventListener("click", function(event){
    event.preventDefault();
    slidePagend.style.marginLeft = "-25%";
    bulletnd[currentnd - 2].classList.remove("active");
    
    stepnd[currentnd - 2].classList.add("active");
    currentnd -= 1;
  });