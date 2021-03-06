
public class NodeMgmt {

	Node head = null;

	// 노드삽입 함수
	public boolean insertNode(int data) {

		if (this.head == null) {
			this.head = new Node(data);
		} else {
			Node findNode = this.head;
			while (true) {
				if (data < findNode.value) {
					if (findNode.left != null) {
						findNode = findNode.left;
					} else {
						findNode.left = new Node(data);
						break;
					}
				} else {
					if (findNode.right != null) {
						findNode = findNode.right;
					} else {
						findNode.right = new Node(data);
						break;
					}
				}
			}
		}
		return true;
	}

	// 노드탐색 함수
	public Node Search(int data) {
		if (this.head == null) {
			return null;
		} else {
			Node findNode = this.head;
			while (findNode != null) {
				if (findNode.value == data) {
					return findNode;
				} else if (data < findNode.value) {
					findNode = findNode.left;
				} else {
					findNode = findNode.right;
				}
			}
			return null;
		}
	}

	// 이진탐색트리 삭제 함수

    public boolean delete(int value) {
        boolean searched = false;
        // Node 가 하나라도 들어가 있을 때
        Node currParentNode = this.head;
        Node currNode = this.head;

        // 코너 케이스1: Node 가 하나도 없을 때
        if (this.head == null) {
            return false;
        } else {
            // 코너 케이스2: (Node 가 단지 하나이고, 해당 Node 삭제 시)
            if (this.head.value == value && this.head.left == null && this.head.right == null) {
                this.head = null;
                return true;
            }

            while (currNode != null) {
                if (currNode.value == value) {
                    searched = true;
                    break;
                } else if (value < currNode.value) {
                    currParentNode = currNode;
                    currNode = currNode.left;
                } else {
                    currParentNode = currNode;
                    currNode = currNode.right;
                }
            }

            if (searched == false) {
                return false;
            }
        }

        // Case1: 삭제할 Node가 Leaf Node인 경우
        if (currNode.left == null && currNode.right == null) {
            if (value < currParentNode.value) {
                currParentNode.left = null;
                currNode = null; // 해당 객체 삭제를 위해, 강제로 null 로 만들어줌
            } else {
                currParentNode.right = null;
                currNode = null; // 해당 객체 삭제를 위해, 강제로 null 로 만들어줌
            }
            return true;
            // Case2: 삭제할 Node가 Child Node를 한 개 가지고 있을 경우 (왼쪽)
        } else if (currNode.left != null && currNode.right == null) {
            if (value < currParentNode.value) {
                currParentNode.left = currNode.left;
                currNode = null;
            } else {
                currParentNode.right = currNode.left;
                currNode = null;
            }
            return true;
            // Case2: 삭제할 Node가 Child Node를 한 개 가지고 있을 경우 (오쪽)
        } else if (currNode.left == null && currNode.right != null) {
            if (value < currParentNode.value) {
                currParentNode.left = currNode.right;
                currNode = null;
            } else {
                currParentNode.right = currNode.right;
                currNode = null;
            }
            return true;
            // Case3-1: 삭제할 Node가 Child Node를 두 개 가지고 있을 경우
            // 상위 코드 조건에 부합하지 않는 경우는 결국 (currNode.left != null && currNode.right != null) 이므로
            // 별도로 else if 로 하기 보다, else 로 작
        } else {

            // 삭제할 Node가 Parent Node 왼쪽에 있을 때
            if (value < currParentNode.value) {

                // 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node 찾기
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;
                while (currNode.left != null) {
                    changeParentNode = currNode;
                    changeNode = currNode.left;
                }
                // 여기까지 실행되면, changeNode 에는 삭제할 Node 의 오른쪽 자식 중, 가장 작은 값을 가진 Node 가 들어있음

                if (changeNode.right != null) {
                    // Case3-1-2: 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                    // Case3-1-1: 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 없을 때
                    changeParentNode.left = null;
                }
                // parent Node 의 왼쪽 Child Node 에 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 changeNode 를 연결
                currParentNode.left = changeNode;
                // parent Node 왼쪽 Child Node 인 changeNode 의 왼쪽/오른쪽 Child Node 를
                // 모두 삭제할 currNode 의 기존 왼쪽/오른쪽 Node 로 변경
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;

                // 삭제할 Node 삭제!
                currNode = null;
                // 삭제할 Node가 Parent Node 오른쪽에 있을 때
            } else {
                // 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node 찾기
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;
                while (changeNode.left != null) {
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }

                if (changeNode.right != null) {
                    // Case3-2-2: 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 있을 때
                    changeParentNode.left = changeNode.right;
                } else {
                    // Case3-2-1: 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 Node의 오른쪽에 Child Node가 없을 때
                    changeParentNode.left = null;
                }

                // parent Node 의 오른쪽 Child Node 에 삭제할 Node의 오른쪽 자식 중, 가장 작은 값을 가진 changeNode 를 연결
                currParentNode.right = changeNode;

                // parent Node 왼쪽 Child Node 인 changeNode 의 왼쪽/오른쪽 Child Node 를
                // 모두 삭제할 currNode 의 기존 왼쪽/오른쪽 Node 로 변경
                if (currNode.right != changeNode) {
                    changeNode.right = currNode.right;
                }
                changeNode.left = currNode.left;
                // 삭제할 Node 삭제!
                currNode = null;
            }
            return true;
        }
    }

}
