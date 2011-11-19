$(
    function(){
        $('input#btn_save').click(
            function(){
                var url = $('input#url').val();
                var title = $('input#title').val();
                var description = $('input#description').val();
                console.log(url);
                $('#btn_save').attr('disabled', true);
                $.ajax(
                    {
                        url : app_root+'/tag/'+tag+'.json',
                        data : {
                            url : url,
                            title : title,
                            description : description
                        },
                        success : function(e){
                            alert('saved!');
                        },
                        error : function(e){
                            alert('error');
                        },
                        complete : function(e){
                            $('#btn_save').attr('disabled', false);
                        },
                        type : 'POST',
                        dataType : 'json'
                    }
                );
            }
        );
        $('input#btn_delete').click(
            function(){
                $('#btn_delete').attr('disabled', true);
                $.ajax(
                    {
                        url : app_root+'/tag/'+tag+'.json',
                        data : {},
                        success : function(e){
                            alert('deleted');
                            location.href = app_root+'/tag'
                        },
                        error : function(e){
                            alert('error');
                        },
                        complete : function(e){
                            $('#btn_delete').attr('disabled', false);
                        },
                        type : 'DELETE',
                        dataType : 'json'
                    }
                );
            }
        );
    }
);

