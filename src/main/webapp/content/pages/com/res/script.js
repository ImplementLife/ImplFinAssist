$.new=function(e){return document.createElement(e)}
function log(val){console.log(val)}
$(function() {
    log("ready");
    $("#button").on("click", function(event) {
        let data = new Map();
        valList.forEach(function(div){data.set(div.name.value, div.val.value);});
        data = JSON.stringify({args: Object.fromEntries(data)});
//        data = JSON.stringify(Object.fromEntries(data));
//        data = Object.fromEntries(data);
        let dt = $("#type option:selected").text();
        $.ajax({
          type: "POST",
          url: $("#url").val(),
          contentType: "application/json; charset=utf-8",
          data: data,
          success: function(result) {
            log("success")
            log(result)
            $("#result").html(result);
          },
          fail: function() {log("fail")},
          always: function() {log("worked")},
          error: function(jqXHR,error, errorThrown) {
            $("#result").html(jqXHR.status);
            log(jqXHR.status);
          }
        });
    });
    console.log("ajax init done");
    $("#add").on("click", function(event) {
      let div = getValFiller();
      $("#values").append(div);
    });
});
var valList = new Array();
function getValFiller() {
    let div = $.new("div");
    let name = $.new("input");
    name.placeholder = "name";
    let value = $.new("input");
    value.placeholder = "value";
    let buttonDeleteLine = $.new("button");
    buttonDeleteLine.innerText = "DEL";
    let divO = {
        name: name,
        val: value
    }
    buttonDeleteLine.onclick = function() {
      div.remove();
      valList.splice(valList.indexOf(divO));
      log("div removed");
    };
    div.append(name);
    div.append(value);
    div.append(buttonDeleteLine);
    valList.push(divO);
    return div;
}