<#ftl encoding="utf-8">
<#include "/global/header.ftl">
<body xmlns="http://www.w3.org/1999/html">
    <h1>All Pokemons</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
            </tr>
        </thead>
        <tbody>
            <#list pokemons as pokemon>
            <tr>
                <td>${pokemon.id}</td>
                <td>${pokemon.name}</td>
                <td>${pokemon.type}</td>
            </tr>
            </#list>
        </tbody>
    </table>

    <hr>
        <h1>Add a Pokemon</h1>
        <form action="/pokemons" method="post">
            <label for="name">Name</label><input type="text" id="name" name="name"
                                                placeholder="Pokemon name"/>
            <label for="type">Type</label><input type="text" id="type" name="type"
                                                placeholder="Pokemon type"/>
            <input type="submit" value="Create Pokemon">
        </form>
</body>
</html>
