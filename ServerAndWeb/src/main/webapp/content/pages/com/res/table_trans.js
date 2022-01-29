$(function() {
    $("#btn_table_loadAll").on('click', function(){
        let allTrans = api.getAllTransactions();
        $("#tbody").empty();
        let i = 1;
        allTrans.forEach(e => {
            let tr = $("<tr></tr>");

            let num = $('<th scope="row"></th>');num.text(i++);
            let val = $("<td></td>");val.text(e.value);
            let date = $("<td></td>");date.text(e.date);
            let cats = $("<td></td>");
            let catsValues = e.categories.map(function(item) {return item['name'];});
            cats.text(catsValues.join('|'));
            let com = $("<td></td>");com.text(e.comments[0].value);

            tr.append(num);
            tr.append(val);
            tr.append(date);
            tr.append(cats);
            tr.append(com);

            $("#tbody").append(tr);
        });

    });
});