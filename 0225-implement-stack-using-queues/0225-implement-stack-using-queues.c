typedef struct {
    int* queue;
    int front;
    int rear;
    int size;
    int capacity;
} MyStack;

MyStack* myStackCreate() {
    MyStack* stack = (MyStack*)malloc(sizeof(MyStack));
    stack->capacity = 100; // Initial capacity
    stack->queue = (int*)malloc(stack->capacity * sizeof(int));
    stack->front = 0;
    stack->rear = 0;
    stack->size = 0;
    return stack;
}

void myStackPush(MyStack* obj, int x) {
    // Expand if needed
    if (obj->size == obj->capacity) {
        obj->capacity *= 2;
        obj->queue = (int*)realloc(obj->queue, obj->capacity * sizeof(int));
    }
    
    // Add new element
    obj->queue[obj->rear] = x;
    obj->rear = (obj->rear + 1) % obj->capacity;
    obj->size++;
    
    // Rotate queue to bring new element to front
    // This reverses the order for all existing elements
    for (int i = 0; i < obj->size - 1; i++) {
        int temp = obj->queue[obj->front];
        obj->front = (obj->front + 1) % obj->capacity;
        obj->queue[obj->rear] = temp;
        obj->rear = (obj->rear + 1) % obj->capacity;
    }
}

int myStackPop(MyStack* obj) {
    int val = obj->queue[obj->front];
    obj->front = (obj->front + 1) % obj->capacity;
    obj->size--;
    return val;
}

int myStackTop(MyStack* obj) {
    return obj->queue[obj->front];
}

bool myStackEmpty(MyStack* obj) {
    return obj->size == 0;
}

void myStackFree(MyStack* obj) {
    free(obj->queue);
    free(obj);
}