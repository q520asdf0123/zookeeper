package cn.enjoy.product.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by VULCAN on 2018/7/25.
 */
public class ServiceRegister {

    private static final String BASE_SERVICES = "/leader";

    private static final String SERVICE_NAME = "/products";

    static final String CONNECT_ADDR = "58.87.106.54:2181";

    public static void register(String address, int port) {
        try {
//            String path = BASE_SERVICES + SERVICE_NAME;
//            ZooKeeper zooKeeper =  new ZooKeeper("58.87.106.54:2181",50000, (watchedEvent)->{});
//            Stat exists = zooKeeper.exists(BASE_SERVICES + SERVICE_NAME, false);
//
//            //先判断服务根路径是否存在
//            if(exists == null) {
//                zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            }
            String server_path = address + ":" + port;
//            zooKeeper.create(path+"/child",server_path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

            ZkClient zkClient = new ZkClient(CONNECT_ADDR, 5000);
            Thread.sleep(3000);
            boolean isPass =  zkClient.exists("/super");
            if (isPass) {
                zkClient.create("/super/path",server_path.getBytes(),CreateMode.EPHEMERAL_SEQUENTIAL);

            } else {
                zkClient.createPersistent("/super", "1234");
            }


            System.out.println("产品服务注册成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
