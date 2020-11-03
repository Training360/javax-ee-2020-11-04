package bank;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class TransferBean {

    @Inject
    private TransferRepository transferRepository;

    @Transactional
    public Transfer createTransfer(CreateTransferCommand command) {
        var transfer = new Transfer();
        transfer.setSrc(command.getSrc());
        transfer.setDest(command.getDest());
        transfer.setAmount(command.getAmount());
        var created = transferRepository.save(transfer);

        // TODO

        return created;
    }

    public List<Transfer> listTransfers() {
        return transferRepository.findAll();
    }
}
