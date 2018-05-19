
    function show_content(content) {
        document.getElementById("main_content").innerHTML = content;
    }

    function addImageListener(){
        var imgs = document.getElementsByTagName("img");
        for(var i=0;i<imgs.length;i++){
            let it = i;
            imgs[i].onclick=function() {
                window.imagelistner.openImage(it);
            }
        }
    }


