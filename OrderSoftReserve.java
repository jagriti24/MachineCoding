import java.util.*;
import java.util.concurrent.TimeUnit;

/*
 @author : jagriti.singh
 @since  : 20/03/21
*/
public class Test {


    public static void main(String args[] ) throws Exception {


        /* assumptions made and decisions taken :
        *
        * order has single item, hence not keeping track of order item at order level
        * payments system is dummy and returns dummy value just for test
        * Made all class static as we are calling it via static method
        *
        */

        // initialising
        Inventory inventory = new Inventory();
        Product product= new Product(inventory);
        Payments payments = new Payments();
        Orders order = new Orders(inventory);


        // **************  adding product ****************/
        product.createProduct("A","chalk", 100);
        product.createProduct("B","shoes", 100);
        product.createProduct("C","pen", 100);
        System.out.println(inventory.getInventory());


        //start order:
        String orderId = "O1";
        String productId = "A";
        String userId = "U1";
        Integer orderQuantity = 10;

        boolean confirm = order.initialise(orderId);
        if(confirm){
            if(payments.initialize(orderId,"233")){
                System.out.println("blocking inventory for product "+productId+" for order :"+orderId);
                if(inventory.blockInventory(userId, productId, orderQuantity,orderId)) {
                    System.out.println(inventory.getInventory());
                    if (payments.confirm(orderId)) {
                        if (order.confirmOrder(userId, orderId, productId)) {
                            System.out.println("Order Confirmed for orderId: " + orderId);
                        } else {
                            System.out.println("Order declined for orderId: " + orderId + " reverting inventory");
                            inventory.updateInventory(productId, inventory.getInventory(productId) + orderQuantity);
                            System.out.println(inventory.getInventory());
                            System.out.println("*********** inventory updated**************");
                        }
                    } else {
                        System.out.println("Payment initialisation failed for orderId: " + orderId);
                    }
                }
                else{
                    System.out.println("blocking inventory failed for orderId: " + orderId);
                }

            }
        }
        else{
            System.out.println("Order initialisation failed for orderId: "+orderId);
        }

    }


    public static class Product{
        Map<String ,Listing> product = new HashMap<>();
        Inventory inventory;

        Product(Inventory inventoryDetails){
            this.inventory = inventoryDetails;
        }

        private void createProduct(String productId, String name, Integer count ){
           //returns given quantity for prod
            if(product.containsKey(productId)){
                System.out.println("Product already exist");
            }
            else {
                Listing productDetails = new Listing(productId, name);
                product.put(productId, productDetails);
                inventory.setInventory(productId,count);
            }
            System.out.println("Product add for "+productId+" with count "+ count);
       }
    }

    public static class Inventory{

        Map<String,Integer> inventory = new HashMap<String,Integer>();
        Map<String,Integer> softReserve = new HashMap<String,Integer>();

        private Map<String,Integer> getInventory(){
            return inventory;
        }
        private Map<String,Integer> getSoftReserve(){
            return softReserve;
        }

        private Integer getInventory(String productId){
            if(inventory.containsKey(productId)){
                if(softReserve.containsKey(productId)) {
                   return inventory.get(productId)-softReserve.get(productId);
                }
                return inventory.get(productId);
            }
            return 0;
        }


        public void setInventory(String productId, Integer count){
            inventory.put(productId,count);
        }

        public void removeSoftInventory(String productId){
            softReserve.remove(productId);
        }


        private boolean blockInventory(String userId, String productId, Integer count, String orderId) {
            Integer temp = inventory.get(productId);
            if (temp-count > 0 ) {
                String softKey = getSoftKey(userId, productId, orderId);
                if (softKey.length() > 0) {
                    setSoftReserve(softKey, count);
//                    updateInventory(productId, inventory.get(productId) - count);
                    return true;
                }
                else{
                    System.out.println("details missing : product:" + productId + " cannot soft reserve order :" + orderId);
                }
            }
            else {
                System.out.println("Inventory is low for product:" + productId + " cannot soft reserve order :" + orderId);
            }
            return false;
        }

        private void updateInventory(String productId, Integer count){
            inventory.replace(productId, count);
        }

        public Integer getSoftReserve(String productId) {
            if(softReserve.containsKey(productId)){
                return softReserve.get(productId);
            }
            return 0;
        }

        private void setSoftReserve(String key, Integer count) {
            this.softReserve = softReserve;
            softReserve.put(key,count);

        }

        private String getSoftKey(String userId, String productId, String orderId){
            if(userId== null || productId==null || orderId== null){
                return "";
            }
            return ""+userId+"-"+orderId+"-"+productId;
        }

    }


    public static class Orders{

        Map<String,Long> order = new HashMap<>();
        Inventory inventory ;

        Orders(Inventory inventory){
            this.inventory = inventory;
        }

        private boolean initialise(String orderId){
            order.put(orderId,System.currentTimeMillis());
            return true;
        }

        private boolean confirmOrder(String userId, String orderId,String productId){
            Long endTime = System.currentTimeMillis();
            Long startTime = order.get(orderId);
            String key = inventory.getSoftKey(userId,productId,orderId);
            Long  timeDiff = TimeUnit.MILLISECONDS.toMinutes(endTime - startTime);

            System.out.println("Start Time: "+ TimeUnit.MILLISECONDS.toMinutes(startTime) + " minutes");
            System.out.println("end Time: "+ TimeUnit.MILLISECONDS.toMinutes(endTime) + " minutes");
            System.out.println("diff: "+ timeDiff + " minutes");

            if(timeDiff>=5){
                System.out.println("Updated soft reserve  before order completion :"+inventory.getSoftReserve());
                inventory.updateInventory(productId,inventory.getInventory(productId)-inventory.getSoftReserve(key));
                System.out.println("Updated inventory after order completion :"+inventory.getInventory());
            }
            inventory.removeSoftInventory(key);
            System.out.println("Updated inventory after order completion :"+inventory.getSoftReserve());

            return timeDiff>=5;
            }
        }

    public static class Payments{

        private boolean initialize(String orderId, String amount){
            return true;
        }

        private boolean confirm(String orderId){
            return true;
        }
    }

    public static class Listing{
        String id;
        String name;

        Listing(String id, String name){
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

