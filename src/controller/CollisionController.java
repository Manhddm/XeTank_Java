package controller;

import controller.interfaces.ICollisionController;
import model.interfaces.IEntity;

public class CollisionController implements ICollisionController {

    @Override
    public void checkCollisions() {

    }

    @Override
    public boolean checkCollision(IEntity entity1, IEntity entity2) {
        if (entity1.isSolid() && entity2.isSolid()) {
            if (entity1.getHitBox().intersects(entity2.getHitBox())) {
                System.out.println("Va Cham thang lon");
                return false;
            }
        }
        return true;
    }

    @Override
    public <T extends IEntity> boolean checkCollisionWithType(IEntity entity, Class<T> entityClass) {
        return false;
    }
}
