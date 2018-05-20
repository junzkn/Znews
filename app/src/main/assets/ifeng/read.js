
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

    function fontSize(size){
        //$("*").css("font-size",size+"px");
        var pp =document.getElementsByTagName("p");
        for(var i=0 ; i<pp.length ; i++){
            pp[i].style.fontSize = size+"px" ;
        }
    }



