package ua.danit.model;

public class Order
{
	private Integer orderId;
	private String itemId;
	private Integer amount;
	private String clientId;
	private Integer cartId;

	public Integer getCartId()
	{
		return cartId;
	}

	public void setCartId(Integer cartId)
	{
		this.cartId = cartId;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public Integer getAmount()
	{
		return amount;
	}

	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
}
