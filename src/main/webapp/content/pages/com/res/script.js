function log(val){console.log(val)}
function div(){return $("<div></div>")}
function btn(){let b=$("<button></button>");return b}
function input(){return $("<input></input>")}
function span(){return $("<span></span>")}
function img(url){let img=$("<img/>");img.attr("src",url);return img;}

$(function() {
    log("on load start");
    $("#btn_addSimpleTransaction").on("click", function(event) {
        $("#transTable").append(formTransactions().form);
    });
    log("on load done");
});

let categories = new Array();
categories.push({id: 1, name: "name1"});
categories.push({id: 2, name: "name2"});
categories.push({id: 3, name: "name3"});

function formTransactions(transaction) {
    let form = div();
    let divInValue = div();
    let inValue = input();
        inValue.attr("placeholder", "сумма");
        divInValue.append(inValue);
        form.append(divInValue);
    let divCategories = div();
        divCategories.addClass("alert");
        divCategories.addClass("alert-primary");
        divCategories.append(formCatSearch(categories));
        form.append(divCategories);

    function formCatSearch(arrCats) {
        let form = span();form.addClass("dropdown");
        let btn = $("<button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton2\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">Категория</button>");

        let dropdown_menu = $("<ul class=\"dropdown-menu dropdown-menu-dark\" aria-labelledby=\"dropdownMenuButton2\"></ul>")
            arrCats.forEach(function(cat){
                let btn_li = $("<li></li>");
                let btn_a = $("<span class=\"dropdown-item active\"></span>");
                btn_li.append(btn_a);
                btn_a.text(cat.name);
                btn_a.on("click", function(event) {form.before(formCategory(cat))});
                dropdown_menu.append(btn_li);
            });
        form.append(btn);
        form.append(dropdown_menu);
        return form;
    }
    function formCategory(category) {
        let spanCat = $("<span class=\"alert alert-primary\" role=\"alert\"></span>")
        let spanVal = span();
            spanVal.text(category.name);
        let spanBtnDel = span();
        let btn_Del = $("<button type=\"button\" class=\"btn btn-outline-danger\"></button>")
            btn_Del.on("click", function() {spanCat.remove();});
            spanBtnDel.append(btn_Del);
            btn_Del.append(img("/res/img/delete_black_18dp.svg"));
            spanCat.append(spanVal);
            spanCat.append(spanBtnDel);
        return spanCat;
    }
    return {form: form}
}