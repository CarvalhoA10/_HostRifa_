const {PrismaClient} = require('@prisma/client')
const prisma = new PrismaClient()
const bcrypt = requrie('bcrypt')

export const createUser = async (req, res) => {

    try{
        const data = req.body

        const user = await prisma.user.create({
            data: {
                name: data.name,
                email: data.email,
                password: await bcrypt.hash(data.password, 10),
                role: 0,
                accountStatus: 0
            }
        })

        return res.status(201).json(user);

    }
    catch{
        return res.status(400).send('Usuário não pode ser criado');
    }

}