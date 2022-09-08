let editor;

window.onload = function() {
    editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.session.setMode("ace/mode/c_cpp");
}

function changeLanguage() {

    let language = $("#languages").val();

    if(language == 'c' || language == 'cpp')editor.session.setMode("ace/mode/c_cpp");
    else if(language == 'php')editor.session.setMode("ace/mode/php");
    else if(language == 'python')editor.session.setMode("ace/mode/python");
    else if(language == 'node')editor.session.setMode("ace/mode/javascript");
    else if(language == 'java')editor.session.setMode("ace/mode/java");
    else if(language == 'node')editor.session.setMode("ace/mode/node");


}

function executeCode() {

   var language = $("#languages").val();
   var file = editor.getSession().getValue();
   console.log(language);
   console.log(file);
    $.ajax({

	  data: {
	            language: $("#languages").val(),
	            code: editor.getSession().getValue()
	        },
	        
        url: "/compiler",

        contentType: "application/json; charset=utf-8",
        
        dataType: "json",
        
        method: "POST",

        data: JSON.stringify(data),      

        success: function(response) {
            $(".output").text(response)
        }
    })
}
