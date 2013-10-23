$(document).ready(function()
{
    
    
    //-----------------------------------------------------------------------------------------------
    //Function to add a project to the system
    $("#addProjectButton").click(function(){
        if($("#projectNameInput").val()!="")
        {
            $("[id='ProjectCreateForm:ProjectCreateFormName']").val($("#projectNameInput").val());
            $("[id='ProjectCreateForm:ProjectCreateFormAction']").click();
            $("[id='navForm:adminNavLink']").click();
            alert($("#projectNameInput").val() + " created");
            
            
        }
        else
        {
            alert("please enter a name first"); 
        }
    });
    
    //-----------------------------------------------------------------------------------------------
    //Function used to hide or reveal the password the user entered for usalbility purposes
    $(".showPassword").click(function()
    {
            if ($(this).html() == "Show Password")
            {
                    $(".showPassword").html("Hide Password");
                    $(this).prev().attr("type","text");
            }else
            {
                    $(".showPassword").html("Show Password");
                    $(this).prev().attr("type","password");
            }
    });
    
    //-----------------------------------------------------------------------------------------------
    //This creates the accordion menu for the different functionality
    $("#accordion").accordion();
    
    //-----------------------------------------------------------------------------------------------
    //Function to add a new user to the system
    $("#createUserFormButton").click(function()
    {
            var username = $("#usernameAdd").val();
            var password = $("#passwordAdd").val();
            var surname = $("#surnameAdd").val();
            var name = $("#nameAdd").val();
            
            var usernameExists= false;
            $("#allUsernames").children().each(function(){
                if($(this).html().trim() == username)
                {
                    alert("Username already in use, please provide a unique username");
                    usernameExists=true;
                }
            });
            if(usernameExists)
                return;
            
            if(username !="" && password !="" && surname !="" && name !="" && !usernameExists)
            {
                password = calcMD5(password);
                
                $("[id='userToCreateForm:userToCreateName']").val(name);
                $("[id='userToCreateForm:userToCreateSurname']").val(surname);
                $("[id='userToCreateForm:userToCreateUsername']").val(username);
                $("[id='userToCreateForm:userToCreatePassword']").val(password);
                $("[id='userToCreateForm:userToCreateButton']").click();
                $("[id='userDBAdd:userDBAddButton']").click();
                $("#usernameAdd").val("");
                $("#passwordAdd").val("");
                $("#surnameAdd").val("");
                $("#nameAdd").val("");
                
                alert("User added successfully");
            }
            else
            {
                alert("please fill in all fields");
            }
    });
    
    //-----------------------------------------------------------------------------------------------
    //Function to update the project name
    $("#projectSaveButton").click(function(){
        var newName = $("#projectEditName").val()
        
        if(newName=="")
        {
            alert("please enter a new name") 
        }
        else
        {
          var projectID = $("#projectEditSelect option:selected").attr("id");
                $("[id='projectEditForm:projectEditName']").val(newName);
                $("[id='projectEditForm:projectEditID']").val(projectID);
                $("[id='projectEditForm:projectEditButton']").click();
                $("[id='updateProjectsDB:updateProjectsDBButton']").click();
                $("[id='navForm:adminNavLink']").click();
                alert("Project Edited Successfully");
        }
            
        
        
    });
    
    //-----------------------------------------------------------------------------------------------
    //Function to update user details
    $("#userUpdateButon").click(function()
    {
            var username = $("#oldUsernameID option:selected").html().trim();
            var password = $("#UserToUpdatePassword").val();
            var surname = $("#userToUpdateSurname").val();
            var name = $("#userToUpdateName").val();
            
            
            
            if(username !="" && password !="" && surname !="" && name !="")
            {
                password = calcMD5(password);
                
                $("[id='userToUpdateForm:userToUpdateName']").val(name);
                $("[id='userToUpdateForm:userToUpdateSurname']").val(surname);
                $("[id='userToUpdateForm:userToUpdateOldUsername']").val(username);
                $("[id='userToUpdateForm:userToUpdatePassword']").val(password);
                $("[id='userToUpdateForm:userToUpdateButton']").click();
                $("[id='userDBUpdate:userDBUpdateButton']").click();
                $("#UserToUpdatePassword").val("");
                $("#userToUpdateSurname").val("");
                $("#userToUpdateName").val("");
                
                
                alert("User added successfully");
            }
            else
            {
                alert("please fill in all fields");
            }
    });
    
    $("#projectDeleteButton").click(function(){
        var id = $("#projectEditSelect option:selected").attr("id");
        var r=confirm("Delete project? This action cannot be undone");
        if (r==false)
            return;
       $("[id='projectDeleteForm:projectDeleteIdBean']").val(id);
       $("[id='projectDeleteForm:projectDeleteIdDB']").val(id);        
       $("[id='projectDeleteForm:projectDeleteFormButton']").click(); 
       
    });
			
});