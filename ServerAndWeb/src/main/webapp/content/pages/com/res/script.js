//===================================================================
$(function() {
    // Using 'superagent' which will return a promise.
    // var superagent = new Object();
    // superagent.get=function(){
    //     return [1,8,74,102];
    // }

    // // This is isn't declared as `async` because it already returns a promise
    // function delay() {// `delay` returns a promise
    //     return new Promise(function(resolve, reject) {// Only `delay` is able to resolve or reject the promise
    //         setTimeout(function() {resolve(42);}, 3000); // After 3 seconds, resolve the promise with value 42
    //     });
    // }


    // async function getAllBooks() {
    //     try {
    //         // await delay();
    //         return await superagent.get();
    //     } catch(error) {
    //         console.log(error);
    //         return null;
    //     }
    // }

    // (async function(){
    //     let books = await getAllBooks();
    //     console.log(books);
    // })();
});
