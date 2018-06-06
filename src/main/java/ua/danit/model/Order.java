package ua.danit.model;

import java.util.UUID;

public class Order
{
	private Integer orderId;

	private String  itemId;

	private Integer amount;

	private String  clientId;

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

	public Order()
	{
		/*
		vol. 1
		 */
		Long fullId = Math.abs(UUID.randomUUID().getMostSignificantBits());
		while ( fullId > Integer.MAX_VALUE ){
			fullId /= 10;
		}

		/*
		vol. 2
		 */
		fullId = (long)(Math.random()*Integer.MAX_VALUE);

		/*
		vol. 3
		 */
		UUID uniqueID = UUID.randomUUID();
		String s = uniqueID.toString().replaceAll("[^0-9]", "");
		int id = Integer.parseInt(s.substring(0, 8));

		this.orderId = Math.toIntExact(fullId);
	}
}
