//$("#btn_addSimpleTransaction").on("click", function(event) {addSimpleTransaction()});

function getAllTransactions() {
    let data;
    $.ajax({
        url: "/user/api/getAllTransactions", type: "POST",
        contentType: "application/json; charset=utf-8",
        data: data,
        success: function(result) {
            log(result)
        }
    });
}
function addSimpleTransaction() {
    let data;
    $.ajax({
        url: "/user/api/addSimpleTransaction", type: "POST",
        contentType: "application/json; charset=utf-8",
        data: data,
        success: function(result) {
            log(result)
        }
    });
}