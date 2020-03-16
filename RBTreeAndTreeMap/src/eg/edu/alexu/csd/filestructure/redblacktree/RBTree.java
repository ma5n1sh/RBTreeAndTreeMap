package eg.edu.alexu.csd.filestructure.redblacktree;

public class RBTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V> {
	
	private INode<T, V> root;
	private INode<T, V> leaf = new RBNode<T, V>(true);

	public INode<T, V> getRoot() {
		return this.root;
	}

	public boolean isEmpty() {
		return (this.root == leaf);
	}

	public void clear() {
		
	}

	public V search(T key) {
		INode<T, V> searchRes = recurSearch(this.root, key);
		return (searchRes == null) ? null : searchRes.getValue();
	}

	public boolean contains(T key) {
		return (recurSearch(this.root, key) == null) ? false : true;
	}

	public void insert(T key, V value) {
		
	}

	public boolean delete(T key) {
		return false;
	}
	
	private INode<T, V> recurSearch(INode<T, V> root, T key) {
		if (root.isNull() || (root.getKey() == key)) {
			return root;
		}

		if (root.getKey().compareTo(key) < 0) {
			recurSearch(root.getLeftChild(), key);
		} else {
			recurSearch(root.getRightChild(), key);
		}
		return null;
	}
	
	private void leftRotate(INode<T, V> node) {
		INode<T, V> right = node.getRightChild();
		node.setRightChild(right.getLeftChild());
		if (right.getLeftChild() != null) {
			right.getLeftChild().setParent(node);
		}
		if (node.getParent() != null) {
			right.setParent(node.getParent());
			if (node.getParent().getLeftChild() == node) {
				node.getParent().setLeftChild(right);
			} else {
				node.getParent().setRightChild(right);
			}
		} else {
			right.setParent(null);
			this.root = right;
		}
		right.setLeftChild(node);
		node.setParent(right);
	}
	
	private INode<T, V> getUncle(INode<T, V> node){
		return ((node != null) && (node.getParent() != null) && (node.getParent().getParent() != null)) ? node.getParent().getParent() : null;
	}
	
	private INode<T, V> getSibling(INode<T, V> node){
		if(node != null) {
			if(node.getParent() != null) {
				if (node.getParent().getLeftChild() == node) {
					return node.getParent().getRightChild();
				} else {
					return node.getParent().getLeftChild();
				}
			}
		}
		return null;
	}

}
