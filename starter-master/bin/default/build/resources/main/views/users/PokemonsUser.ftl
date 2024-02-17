<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pokémons de l'utilisateur</title>
</head>
<body>


    
    <#if !u??>
        <h1>Vous n'êtes pas connecté. Accès interdit.</h1>
    <#else>
        <h1>Bonjour, ${u.username}</h1>
        <hr>
        <#include "/global/header.ftl">

        <h1>Pokémons de l'utilisateur</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
        
                <!-- Autres attributs du Pokémon -->
            </tr>
        </thead>
        <tbody>
            <#list PokemonsUser as pokemon>
                <tr>
                    <td>${pokemon.id}</td>
                    <td>${pokemon.name}</td>
                   
                    <!-- Afficher les autres attributs du Pokémon -->
                </tr>
            </#list>
        </tbody>
    </table>
        
    </#if>
    
</body>
</html>
