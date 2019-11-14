package com.bitree;

/**
 *
 * @author ZHAOJIE644
 * @date 2019-11-5
 * @since 1.0.0
 */
public class RedBlackTree extends BiTree{

    private RedBlackTreeNode root;

    public void insert(int[] datas){
        for(int data:datas){
            this.insert(data);
        }
    }

    public void insert(int x){
        RedBlackTreeNode cur = this.root;
        RedBlackTreeNode node = new RedBlackTreeNode();
        node.setData(x);
        //插入的根节点
        if(cur == null){
            root = node;
            node.setColor(RedBlackTreeNode.COLOR_BLACK);
            return;
        }else {
            //从根节点找到插入位置
            while (true) {
                if (x < cur.getData()) {
                    if (cur.getLeft() == null) {
                        cur.setLeft(node);
                        node.setParent(cur);
                        break;
                    }else{
                        cur = cur.getLeft();
                    }
                } else {
                    if (cur.getRight() == null) {
                        cur.setRight(node);
                        node.setParent(cur);
                        break;
                    }else{
                        cur = cur.getRight();
                    }
                }
            }
        }
        //调整节点和颜色。
        rbInsertFix(node);
    }


    /**
     * 插入的左节点，调整
     * @param node
     */
    public void rbInsertFix(RedBlackTreeNode node){
        while(node != root && node.getParent().getColor() == RedBlackTreeNode.COLOR_RED){
            //如果父亲节点为红色，则需要进行调整红黑树结构
            RedBlackTreeNode uncle = null;
            if(node.getParent().getParent().getLeft() == node.getParent()){
                uncle = node.getParent().getParent().getRight();
                //分uncle为红色或者黑色,红色则上下节点变色，变完后需要向上调涨
                if(uncle != null && uncle.getColor() == RedBlackTreeNode.COLOR_RED){
                    uncle.setColor(RedBlackTreeNode.COLOR_BLACK);
                    node.getParent().setColor(RedBlackTreeNode.COLOR_BLACK);
                    if(node.getParent().getParent() != root) {
                        node.getParent().getParent().setColor(RedBlackTreeNode.COLOR_RED);
                    }
                    node = node.getParent().getParent();
                }else{
                    //黑色，则需要旋转操作
                    //祖先-左-父亲-右-当前节点,当前节点在右子树上，左旋
                    if(node.getParent().getRight() == node){
                        node = node.getParent();
                        rotateLeft(node);
                    }

                    //祖先-左-父亲-左-当前节点
                    //父亲和祖先节点变色
                    node.getParent().setColor(RedBlackTreeNode.COLOR_BLACK);
                    node.getParent().getParent().setColor(RedBlackTreeNode.COLOR_RED);
                    //祖先节点右旋
                    rotateRight( node.getParent().getParent());
                }
            }else{
                uncle = node.getParent().getParent().getLeft();
                //分uncle为红色或者黑色,红色则上下节点变色，变完后需要向上调涨
                if(uncle!= null && uncle.getColor() == RedBlackTreeNode.COLOR_RED){
                    uncle.setColor(RedBlackTreeNode.COLOR_BLACK);
                    node.getParent().setColor(RedBlackTreeNode.COLOR_BLACK);
                    if(node.getParent().getParent() != root) {
                        node.getParent().getParent().setColor(RedBlackTreeNode.COLOR_RED);
                        node = node.getParent().getParent();
                    }
                }else{
                    //黑色，则需要旋转操作
                    //祖先-右-父亲-左-当前节点,当前节点在左子树上，右旋
                    if(node.getParent().getLeft() == node){
                        node = node.getParent();
                        rotateRight(node);
                    }

                    //祖先-右-父亲-右-当前节点
                    //父亲和祖先节点变色
                    node.getParent().setColor(RedBlackTreeNode.COLOR_BLACK);
                    node.getParent().getParent().setColor(RedBlackTreeNode.COLOR_RED);
                    //祖先节点左旋
                    rotateLeft(node.getParent().getParent());
                }
            }
        }
    }


    /**
     * 左旋，右子节点存在，以当前节点为支点
     * @param node
     */
    public void rotateLeft(RedBlackTreeNode node){

        if(node == null || node.getRight() == null){
            return ;
        }

        RedBlackTreeNode child = node.getRight();
        //如果是根节点，

        //子节点的左边节点先挂到当前节点上
        node.setRight(child.getLeft());
        if(child.getLeft() != null){
            child.getLeft().setParent(node);
        }
        RedBlackTreeNode parent = node.getParent();
        child.setParent(parent);
        if(parent == null){
            //根节点，
            root = child;
        }else{
            //不是根节点,parent节点位于左子树上
            if(parent.getLeft() == node){
               parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }
        child.setLeft(node);
        node.setParent(child);
    }


    /**
     * 右旋转，左子节点存在，以当前节点为支点
     * @param node
     */
    public void rotateRight(RedBlackTreeNode node){

        if(node == null || node.getLeft() == null){
            return ;
        }

        RedBlackTreeNode child = node.getLeft();

        //子节点的左边节点先挂到当前节点上

        node.setLeft(child.getRight());
        if(child.getRight() != null){
            child.getRight().setParent(node);
        }
        RedBlackTreeNode parent = node.getParent();
        child.setParent(parent);
        if(parent == null){
            //根节点，
            root = child;
        }else{
            //不是根节点,parent节点位于左子树上
            if(parent.getLeft() == node){
                parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }
        child.setRight(node);
        node.setParent(child);
    }


    @Override
    public RedBlackTreeNode getRoot() {
        return root;
    }

    public void setRoot(RedBlackTreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new int[]{18,22,9,1,5});
        System.out.println(tree);
    }
}