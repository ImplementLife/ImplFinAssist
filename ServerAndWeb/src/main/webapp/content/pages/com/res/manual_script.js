var categoriesForTransaction = new Array();
function addCat(cat) {categoriesForTransaction.push(cat)};
function remCat(cat) {categoriesForTransaction.splice(categoriesForTransaction.indexOf(cat), 1)};
function getAllCats() {return categoriesForTransaction;};
function clearCats() {categoriesForTransaction = new Array();}

function categoriesForm(categories) {
    let mainDiv = $('<div class="input-group mb-3"></div>');
    let listCats = $('<span class="form-control ps-0"></span>');
    let btn_currentCat = $('<button type="button" class="btn btn-outline-secondary">Action</button>');
    let toggleDD = $('<button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false"><span class="visually-hidden">Toggle Dropdown</span></button>');
    
    let catList = $('<ul class="dropdown-menu dropdown-menu-end"></ul>');
    let liNewCat = createLiNewCat();
    let liDevider = $('<li><hr class="dropdown-divider"></li>')
   
    mainDiv.append(listCats);
    mainDiv.append(btn_currentCat);
    mainDiv.append(toggleDD);
    mainDiv.append(catList);

    catList.append(liDevider);
    catList.append(liNewCat);

    function addToListCats(cat) {
        let catView = $('<span catEl="t" class="border text-nowrap text-muted rounded-3 m-2 p-1 text-center h-100"></span>');
        catView.text(cat.name);
        catView.on('click', function(){catView.remove();addCatToDDM(cat);remCat(cat);});
        addCat(cat);
        listCats.append(catView);
    }
    function toggleCurrentCat() {
        btn_currentCat.text($(this).val())
    }
    function createLiNewCat() {
        let liNewCat = $("<li></li>");
        let btn_NewCat = $('<button type="button" class="dropdown-item">Add</button>');
        liNewCat.append(btn_NewCat);
        return liNewCat;
    }
    function addCatToDDM(cat) {
        let li = $("<li></li>");
        let btn = $('<button class="dropdown-item"></button>');
        btn.text(cat.name);
        btn.on('click', function(){addToListCats(cat);li.remove();});
        li.append(btn);
        liDevider.before(li);
    }
    
    categories.forEach(element => {addCatToDDM(element);});
    return {
        element:mainDiv,
        addCat:function(cat){addToListCats(cat)},
    };
}

function main() {
    log("send");
    let ac = api.getAllCategories();
    log(ac);
    let cF
    if(ac.length >= 1) {
        cF = categoriesForm(ac);
        $("#tst").append(cF.element);
    }
    //Новая категория
    $("#in_newCat").val('');
    $("#btn_newCat").on('click', function(){
        let name = $("#in_newCat").val();
        if(name == '') {
            alert("Название пустое"); 
            return;
        }
        let resp = api.addNewCategory({name:name});
        if(cF == null) {
            cF = categoriesForm([]);
            $("#tst").append(cF.element); 
        }
        cF.addCat(resp);
        $("#in_newCat").val('');
    });
    //Кнопка ADD / CLEAR
    $("#btn_add").on('click',function(){sendForm()})
    $("#btn_clear").on('click',function(){clearForm()})

    function clearForm() {
        $("#in_value").val('');
        $("#in_newCat").val('');
    }
    function sendForm() {
        api.addSimpleTransaction({
            date:Date.now(),
            comments:[{value:$("#in_comment").val(),date:Date.now()}],
            value:$("#in_value").val(),
            categories:getAllCats(),
        });
        clearForm();
    }
}
$(function(){main();});

