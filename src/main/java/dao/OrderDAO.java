package dao;

public interface OrderDAO {

    public boolean addOrder(Order order);

    public boolean deleteOrder(int oderid);

    public boolean updateOrder(Order o);


    public List<Order> listALlOrders();
}
