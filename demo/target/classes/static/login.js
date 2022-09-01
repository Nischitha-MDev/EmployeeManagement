function login()
        {//creating object
            var body = {
                id: document.getElementById("Id").value,//storing id
                name : document.getElementById("Name").value//storing name
            }

            const req = new XMLHttpRequest();

            req.open("POST", "http://localhost:8080/authenticate");
            
            req.setRequestHeader("Content-Type", "application/json");
            req.onload = function(e) {//wait
                console.log("hello");//browser
                
                if (JSON.parse(req.response)["IsValidEmployye"] == true)//JSON.parse -> convert Json string to Javascript object
            	{
                	window.location.href="http://localhost:8080/home.html?id="+body.id+"&name="+body.name+"";
            	}
            else
            	{
            	alert("Invalid User");
            	}
            };
            req.send(JSON.stringify(body));//JSON.stringify -> convert Javascript object to json string
            
           

           
        }