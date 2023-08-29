   // selecting required element
   const element = document.querySelector(".pagination ul");

   let maxList = document.querySelectorAll("#slideShow1 tr").length;
   let perPage = 5;
   let totalPages = Math.ceil(maxList / perPage);
   let page = 1;
   let start = 0;
   let end = perPage;


   //calling function with passing parameters and adding inside element which is ul tag
   element.innerHTML = createPagination(totalPages, page);

   function createPagination(totalPages, page) {

       show()

       function show() {
           $("#slideShow1 tr").each(function(index, slide) {
               if (index >= start && index < end) {
                   $(slide).show();
               } else {
                   $(slide).hide();
               }
           })
       }

       function runPage() {
           start = (page - 1) * perPage;
           end = page * perPage;
       }
       runPage();
       show()







       let liTag = '';
       let active;
       let beforePage = page;
       let afterPage = page;
       if (page > 1) { //show the next button if the page value is greater than 1
           liTag += `<li class="btn-prev" onclick="createPagination(totalPages, ${page - 1})"><span><i class="fas fa-angle-left"></i> Prev</span></li>`;
       }

       if (page > 2) { //if page value is less than 2 then add 1 after the previous button
           liTag += `<li class="first numb" onclick="createPagination(totalPages, 1)"><span>1</span></li>`;
           if (page > 3) { //if page value is greater than 3 then add this (...) after the first li or page
               liTag += `<li class="dots"><span>...</span></li>`;
           }
       }

       // how many pages or li show before the current li
       if (page == totalPages) {
           beforePage = beforePage - 1;
       } else if (page == totalPages - 1) {
           beforePage = beforePage;
       }
       // how many pages or li show after the current li
       if (page == 1) {
           afterPage = afterPage + 1;
       } else if (page == 2) {
           afterPage = afterPage;
       }

       for (var plength = beforePage; plength <= afterPage; plength++) {
           if (plength > totalPages) { //if plength is greater than totalPage length then continue
               continue;
           }
           if (plength == 0) { //if plength is 0 than add +1 in plength value
               plength = plength + 1;
           }
           if (page == plength) { //if page is equal to plength than assign active string in the active variable
               active = "active";
           } else { //else leave empty to the active variable
               active = "";
           }
           liTag += `<li class="numb ${active}" onclick="createPagination(totalPages, ${plength})"><span>${plength}</span></li>`;
       }

       if (page < totalPages - 1) { //if page value is less than totalPage value by -1 then show the last li or page
           if (page < totalPages - 2) { //if page value is less than totalPage value by -2 then add this (...) before the last li or page
               liTag += `<li class="dots"><span>...</span></li>`;
           }
           liTag += `<li class="last numb" onclick="createPagination(totalPages, ${totalPages})"><span>${totalPages}</span></li>`;
       }

       if (page < totalPages) { //show the next button if the page value is less than totalPage(20)
           liTag += `<li class="btn-next" onclick="createPagination(totalPages, ${page + 1})"><span>Next <i class="fas fa-angle-right"></i></span></li>`;
       }



       element.innerHTML = liTag; //add li tag inside ul tag
       return liTag; //reurn the li tag


   }


   //tab 2
   const element2 = document.querySelector(".pagination2 ul");

   let maxList2 = document.querySelectorAll("#slideShow2 tr").length;
   let perPage2 = 5;
   let totalPages2 = Math.ceil(maxList2 / perPage2);
   let page2 = 1;
   let start2 = 0;
   let end2 = perPage2;

   //calling function with passing parameters and adding inside element which is ul tag
   element2.innerHTML = createPagination2(totalPages2, page2);

   function createPagination2(totalPages2, page2) {

       show2()

       function show2() {
           $("#slideShow2 tr").each(function(index2, slide2) {
               if (index2 >= start2 && index2 < end2) {
                   $(slide2).show();
               } else {
                   $(slide2).hide();
               }
           })
       }

       function runPage2() {
           start2 = (page2 - 1) * perPage2;
           end2 = page2 * perPage2;
       }
       runPage2();
       show2()







       let liTag2 = '';
       let active2;
       let beforePage2 = page2;
       let afterPage2 = page2;
       if (page2 > 1) { //show the next button if the page value is greater than 1
           liTag2 += `<li class="btn-prev" onclick="createPagination2(totalPages2, ${page2 - 1})"><span><i class="fas fa-angle-left"></i> Prev</span></li>`;
       }

       if (page2 > 2) { //if page value is less than 2 then add 1 after the previous button
           liTag2 += `<li class="first numb" onclick="createPagination2(totalPages2, 1)"><span>1</span></li>`;
           if (page2 > 3) { //if page value is greater than 3 then add this (...) after the first li or page
               liTag2 += `<li class="dots"><span>...</span></li>`;
           }
       }

       // how many pages or li show before the current li
       if (page2 == totalPages2) {
           beforePage2 = beforePage2 - 1;
       } else if (page2 == totalPages2 - 1) {
           beforePage2 = beforePage2;
       }
       // how many pages or li show after the current li
       if (page2 == 1) {
           afterPage2 = afterPage2 + 1;
       } else if (page2 == 2) {
           afterPage2 = afterPage2;
       }

       for (var plength = beforePage2; plength <= afterPage2; plength++) {
           if (plength > totalPages2) { //if plength is greater than totalPage length then continue
               continue;
           }
           if (plength == 0) { //if plength is 0 than add +1 in plength value
               plength = plength + 1;
           }
           if (page2 == plength) { //if page is equal to plength than assign active string in the active variable
               active2 = "active";
           } else { //else leave empty to the active variable
               active2 = "";
           }
           liTag2 += `<li class="numb ${active2}" onclick="createPagination2(totalPages2, ${plength})"><span>${plength}</span></li>`;
       }

       if (page2 < totalPages2 - 1) { //if page value is less than totalPage value by -1 then show the last li or page
           if (page2 < totalPages2 - 2) { //if page value is less than totalPage value by -2 then add this (...) before the last li or page
               liTag2 += `<li class="dots"><span>...</span></li>`;
           }
           liTag2 += `<li class="last numb" onclick="createPagination2(totalPages2, ${totalPages2})"><span>${totalPages2}</span></li>`;
       }

       if (page2 < totalPages2) { //show the next button if the page value is less than totalPage(20)
           liTag2 += `<li class="btn-next" onclick="createPagination2(totalPages2, ${page2 + 1})"><span>Next <i class="fas fa-angle-right"></i></span></li>`;
       }



       element2.innerHTML = liTag2; //add li tag inside ul tag
       return liTag2; //reurn the li tag


   }