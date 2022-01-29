
const api = new Object();
function log(val){console.log(val)};
function send(url, data) {
    let res;
    let jsData = JSON.stringify(data);
    $.ajax({
        url: url, type: "POST", contentType: "application/json; charset=utf-8", success: function(result) {res = result;}, async: false,
        data: jsData
    });
    return res;
}
api.getAllTransactions = function() {
    return send("/user/api/getAllTransactions");
}
api.addSimpleTransaction = function(dataObj) {
    return send("/user/api/addSimpleTransaction", dataObj);
}
api.getAllCategories = function() {
    return send("/user/api/getAllCategories");
}
api.addNewCategory = function(category) {
    return send("/user/api/addNewCategory", category);
}