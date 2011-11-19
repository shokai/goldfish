$(
    function(){
        $('input#btn_add_tag').click(
            function(){
                var tag = $('input#text_tag').val();
                location.href = app_root+'/tag/'+tag+'/edit';
            }
        );
    }
);