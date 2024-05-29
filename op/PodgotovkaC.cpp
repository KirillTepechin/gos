#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

struct Tour {
    int num;
    char country[10];
    struct Tour* next;
    struct Tour* prev;
};

struct Tour* head = NULL;
struct Tour* tail = NULL;

void addToMiddle() {
    struct Tour* newTour = (struct Tour*)malloc(sizeof(struct Tour));
    newTour->prev = NULL;
    newTour->next = NULL;
    printf("Tour num: ");
    scanf("%d", &newTour->num);
    printf("Tour country: ");
    scanf("%s", newTour->country);

    //Если список пуст
    if (head == NULL) {
        head = newTour;
        tail = newTour;
    }
    else {
        struct Tour* current = head;
        //Ищем элемент больший нового
        while (current != NULL && strcmp(newTour->country, current->country) > 0) {
            current = current->next;
        }
        //Вставка в конец, если эл самый большой
        if (current == NULL) {
            newTour->prev = tail;
            tail->next = newTour;
            tail = newTour;
        }
        else {
            if (current->prev != NULL) {
                current->prev->next = newTour;
                newTour->prev = current->prev;
            }
            else {
                head = newTour;
            }
            current->prev = newTour;
            newTour->next = current;
        }
    }
}

void printList() {
    Tour* current = head;
    if (current == NULL) {
        printf("List is empty.\n");
    }
    while (current != NULL) {
        printf("%d\t%s\n", current->num, current->country);
        current = current->next;
    }
}
void printListReverse() {
    Tour* current = tail;
    if (current == NULL) {
        printf("List is empty.\n");
    }
    while (current != NULL) {
        printf("%d\t%s\n", current->num, current->country);
        current = current->prev;
    }
}

int main()
{
    int choice;
    printf("1. Add Tour to list\n");
    printf("2. Print list\n");
    printf("3. Print list reverse\n");
    printf("4. Exit\n");
    do {
        printf("\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
        case 1:
            addToMiddle();
            break;
        case 2:
            printList();
            break;
        case 3:
            printListReverse();
            break;
        case 4:
            printf("Exiting program.\n");
            break;
        default:
            printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 4);

    return 0;
}
