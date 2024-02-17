<#ftl encoding="utf-8">
<head>
    <title></title>
    <link rel="stylesheet" href="style.css">
</head>
<body xmlns="http://www.w3.org/1999/html">

<#if !u??>
    <h1>Vous n'êtes pas connecté. Accès interdit.</h1>
    <#include "/global/header.ftl">
<#else>
    <h1>Bonjour, ${u.username}</h1>
    <hr>
</#if>
    

    <h1>All Users</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Pokémon</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td><a href="/users/${user.id}/pokemons">Voir les Pokémon</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

    <hr>
    <h1>Ajouter un utilisateur</h1>
    <form action="/users" method="post">
        <label for="username">Nom d'utilisateur</label><input type="text" id="username" name="username"
                                                              placeholder="Nom d'utilisateur"/>
        <label for="password">Mot de passe</label><input type="password" id="password" name="password"
                                                          placeholder="Mot de passe"/>
        <input type="submit" value="Créer l'utilisateur">
    </form>

    <hr>
    <a id = "dec" href="/logout">Déconnexion</a>
    


</body>

</html>
