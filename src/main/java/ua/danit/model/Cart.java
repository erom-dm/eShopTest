package ua.danit.model;

public class Cart
{

	private Integer cartID;
	private Long cartTime;

	public Cart()
	{
		this.cartID = genereate();
	}

	private Integer genereate()
	{
		//TODO
		return null;
	}

	public Integer getCartID()
	{
		return cartID;
	}

	public void setCartID(Integer cartID)
	{
		this.cartID = cartID;
	}

	public Long getCartTime()
	{
		return cartTime;
	}

	public void setCartTime(Long cartTime)
	{
		this.cartTime = cartTime;
	}
}
