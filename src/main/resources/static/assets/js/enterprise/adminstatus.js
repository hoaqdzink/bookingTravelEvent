let arrow = document.querySelectorAll(".arrow")

for (var i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e) => {
        let arrowParent = e.target.parentElement.parentElement;
        arrowParent.classList.toggle("showMenu");
    });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".fa-bars");
sidebarBtn.addEventListener("click", () => {
    sidebar.classList.toggle("close")
});

function Test() {
    const test = document.querySelectorAll('#test')
    const btn = document.querySelectorAll(".button_tour")

    for (var i = 0; i < test.length; i++) {
        if (test[i].innerHTML === "DUYET") {
            test[i].style.background = "#34A853";
            test[i].style.color = "white"
            test[i].innerHTML = "Đã duyệt";

        }
        if (test[i].innerHTML === "CHUA") {
            test[i].style.background = "yellow";
            test[i].innerHTML = "Đang chờ";

        }
        if (test[i].innerHTML === "HUY") {
            test[i].style.background = "red";
            test[i].style.color = "white"
            test[i].innerHTML = "Đã hủy";

        }
    }

    const test2 = document.querySelectorAll("#detail")

    for (var i = 0; i < test2.length; i++) {
        if (test2[i].innerHTML === "duyet") {
            test2[i].style.border = "2px solid greenyellow";
            test2[i].style.color = "greenyellow";
            test2[i].innerHTML = "da duyet";
        }
        if (test2[i].innerHTML === "chua") {
            test2[i].style.border = "2px solid yellow";
            test2[i].style.color = "yellow";
            test2[i].innerHTML = "chua duyet";
        }
        if (test2[i].innerHTML === "huy") {
            test2[i].style.border = "2px solid red";
            test2[i].style.color = "red";
            test2[i].innerHTML = "Huy";
        }
    }

    const test3 = document.querySelectorAll('#statusOrder')
    for (var i = 0; i < test3.length; i++) {
        if (test3[i].innerHTML === "DUYET") {
            test3[i].style.color = "#00800094"
            test3[i].innerHTML = "Đã hoàn tất";

        }
        if (test3[i].innerHTML === "CHUA") {
            test3[i].style.color = "#ffcc009e"
            test3[i].innerHTML = "Đang chờ...";

        }
        if (test3[i].innerHTML === "HUY") {
            test3[i].style.color = "red"
            test3[i].innerHTML = "Đã hủy";

        }
    }

    const test4 = document.querySelectorAll('#trangThaiComment')
    for (var i = 0; i < test4.length; i++) {
        if (test4[i].innerHTML === "true") {
            test4[i].style.background = "#34A853";
            test4[i].style.color = "white"
            test4[i].innerHTML = "Đã phản hồi";

        }
        if (test4[i].innerHTML === "false") {
            test4[i].style.background = "yellow";
            test4[i].innerHTML = "Đang chờ phản hồi...";

        }
    }

    const test5 = document.querySelectorAll('#trangThaiActive')
    for (var i = 0; i < test5.length; i++) {
        if (test5[i].innerHTML === "true") {
            test5[i].innerHTML = `<input onclick="return false" checked type="checkbox" />`;

        }
        if (test5[i].innerHTML === "false") {
            test5[i].innerHTML = `<input onclick="return false" type="checkbox" />`;

        }
    }

}