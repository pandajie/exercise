package com.bitree;

/**
 * @author ZHAOJIE644
 * @date 2019-10-29
 * @since 1.0.0
 */
public class BiTree{

    /**
     * 前序+中序构建二叉树
     * @param pre
     * @param mid
     */
    public void buildTree(int[] pre,int[] mid){
        BiNode node = build(pre,mid,0,pre.length-1,0,mid.length-1);
        setRoot(node);
    }


    /**
     * @param pre
     * @param mid
     * @param pstart
     * @param pend
     * @param mstart
     * @param mend
     * @return
     */
    public BiNode build(int[] pre,int[] mid,int pstart,int pend,int mstart,int mend){
        BiNode  node = null;
        if(pstart<=pend && mstart<=mend){
            //前序找到当前节点位置，pre第一个
            int data = pre[pstart];
            node = new BiNode(data);
            //找到中序的节点
            for(int i = mstart;i<=mend;i++){
                if(mid[i] == data){
                    int len = i - mstart;
                    BiNode left = build(pre,mid,pstart+1,pstart+len,mstart,i-1);
                    BiNode right = build(pre,mid,pstart+len+1,pend,i+1,mend);
                    node.setLeft(left);
                    node.setRight(right);
                }
            }
        }
        return node;
    }


    public static BiTree defaultTree(){
        BiTree biTree = new BiTree();
        BiNode root = new BiNode(1);
        biTree.setRoot(root);
        BiNode left = new BiNode(2);
        BiNode right = new BiNode(3);
        BiNode leftLeft = new BiNode(4);
        BiNode rightRight = new BiNode(5);
        BiNode rightLeft = new BiNode(6);
        BiNode rightLeftRight = new BiNode(7);
        BiNode rightLeftRightRight = new BiNode(10);
        rightLeftRight.setRight(rightLeftRightRight);
        left.setLeft(leftLeft);
        right.setRight(rightRight);
        right.setLeft(rightLeft);
        rightLeft.setRight(rightLeftRight);
        root.setLeft(left);
        root.setRight(right);
        return biTree;
    }

    public static BiTree defaultTree2(){
        int[] pre = {1,2,6,3,4,5};
        int[] mid = {6,2,3,1,5,4};
        BiTree tree = new BiTree();
        tree.buildTree(pre,mid);
        return tree;
    }


    private BiNode root;

    public BiNode getRoot() {
        return root;
    }

    public void setRoot(BiNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        int[] pre = {1,2,6,3,4,5};
        int[] mid = {6,2,3,1,5,4};
        BiTree tree = new BiTree();
        tree.buildTree(pre,mid);
        System.out.println(tree);
    }
}
