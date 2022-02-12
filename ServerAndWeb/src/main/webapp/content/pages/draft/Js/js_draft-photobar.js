addPhoto()
function addPhoto() {
    let divWrapper = document.createElement("div");
    let photoAdd = document.getElementById("add_photo");

    divWrapper.className = "divWrapper";
    photoAdd.append(divWrapper);

    divWrapper.onclick = function () {
        a();
    };
    function a() {
        let newPhoto = document.createElement("div");
        newPhoto.className = "newPhoto";

        photoAdd.prepend(newPhoto);
    };
}