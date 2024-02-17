
public class Trade{
 
	private int id;
    private int userId;
    private int pokemonId;
    private int tradeUserId;
    private int tradePokemonId;
    
    
    public Trade() {
 		
 	}
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPokemonId() {
		return pokemonId;
	}
	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}
	public int getTradeUserId() {
		return tradeUserId;
	}
	public void setTradeUserId(int tradeUserId) {
		this.tradeUserId = tradeUserId;
	}
	public int getTradePokemonId() {
		return tradePokemonId;
	}
	public void setTradePokemonId(int tradePokemonId) {
		this.tradePokemonId = tradePokemonId;
	}
    
    
}