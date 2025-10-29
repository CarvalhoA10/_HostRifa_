-- CreateTable
CREATE TABLE "User" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "role" INTEGER NOT NULL,
    "accountStatus" INTEGER NOT NULL,
    "createdAt" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" DATETIME NOT NULL
);

-- CreateTable
CREATE TABLE "Raffle" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "raffleName" TEXT NOT NULL,
    "homologatedPlatform" TEXT NOT NULL,
    "homologatedCode" TEXT NOT NULL,
    "raffleImage" TEXT NOT NULL,
    "prizeImage" TEXT NOT NULL,
    "price" REAL NOT NULL,
    "ownerId" INTEGER NOT NULL,
    "createdAt" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" DATETIME NOT NULL,
    CONSTRAINT "Raffle_ownerId_fkey" FOREIGN KEY ("ownerId") REFERENCES "User" ("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

-- CreateIndex
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Raffle_homologatedCode_key" ON "Raffle"("homologatedCode");
