<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            EHR System Login
        </title>
        <style>
            body {

            font-family: Arial, sans-serif;

            background-color: #f4f4f4;

            display: flex;

            justify-content: center;

            align-items: center;

            height: 100vh;

            margin: 0;

        }


        .login-container {

            width: 350px;

            background: white;

            padding: 30px;

            border-radius: 8px;

            box-shadow: 0 0 10px rgba(0,0,0,0.2);

        }


        h2 {

            text-align: center;

            margin-bottom: 20px;

        }


        label {

            display: block;

            margin-top: 10px;

        }


        input {

            width: 100%;

            padding: 10px;

            margin-top: 5px;

            margin-bottom: 15px;

            box-sizing: border-box;

        }


        button {

            width: 100%;

            padding: 10px;

            border: none;

            background-color: #0078d4;

            color: white;

            cursor: pointer;

        }


        button:hover {

            background-color: #005ea6;

        }


        .error {

            color: red;

            text-align: center;

            margin-bottom: 15px;

        }


        .success {

            color: green;

            text-align: center;

            margin-bottom: 15px;

        }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>
                EHR System Login
            </h2>
            <% if (request.getParameter("error") != null) { %>
            <div class="error">
                Invalid Username or Password
            </div>
            <% } %>
            <% if (request.getParameter("logout") != null) { %>
            <div class="success">
                Successfully Logged Out
            </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="hidden"

               name="${_csrf.parameterName}"

               value="${_csrf.token}" />
                <label>
                    Username
                </label>
                <input type="text"

               name="username"

               required />
                <label>
                    Password
                </label>
                <input type="password"

               name="password"

               required />
                <button type="submit">
                    Login
                </button>
            </form>
        </div>
    </body>
</html>
