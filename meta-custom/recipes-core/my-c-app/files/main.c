#include <stdio.h>
#include <unistd.h>

int main() {
  while (1) {
    printf("Hello from SystemD C Service!\n");
    sleep(10);
  }
  return 0;
}
