package com.wood.FactoryDefense;

import jdk.nashorn.internal.ir.Statement;

public class BlockTypes {
    public class BaseBlock{
        public static String name;
        public static boolean canDestroyed;
        BaseBlock(){
            name = this.getClass().getSimpleName();
        }
    }

    public class BaseBuildingBlock extends BaseBlock {
        BaseBuildingBlock(){
            super();
            canDestroyed = true;
        }
    }
}
